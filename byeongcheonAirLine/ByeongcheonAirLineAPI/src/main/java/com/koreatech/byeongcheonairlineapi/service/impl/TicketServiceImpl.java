package com.koreatech.byeongcheonairlineapi.service.impl;

import com.koreatech.byeongcheonairlineapi.dto.LoginDto;
import com.koreatech.byeongcheonairlineapi.dto.Model.RequestCreateTicket;
import com.koreatech.byeongcheonairlineapi.dto.Model.ResponseTicket;
import com.koreatech.byeongcheonairlineapi.dto.domain.Customer;
import com.koreatech.byeongcheonairlineapi.dto.domain.Member;
import com.koreatech.byeongcheonairlineapi.dto.domain.Ticket;
import com.koreatech.byeongcheonairlineapi.mapper.CustomerMapper;
import com.koreatech.byeongcheonairlineapi.mapper.MemberMapper;
import com.koreatech.byeongcheonairlineapi.mapper.TicketMapper;
import com.koreatech.byeongcheonairlineapi.service.JwtService;
import com.koreatech.byeongcheonairlineapi.service.TicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class TicketServiceImpl implements TicketService {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final TicketMapper ticketMapper;
    private final MemberMapper memberMapper;
    private final CustomerMapper customerMapper;

    private final JwtService jwtService;
    @Autowired
    public TicketServiceImpl(TicketMapper ticketMapper, MemberMapper memberMapper, CustomerMapper customerMapper, JwtService jwtService) {
        this.ticketMapper = ticketMapper;
        this.memberMapper = memberMapper;
        this.customerMapper = customerMapper;
        this.jwtService = jwtService;
    }

    @Override
    public List<Ticket> findAll() {
        return ticketMapper.findAll();
    }

    //비회원 id로 티켓 조회
    @Override
    public List<ResponseTicket> findByCustomer(int id) {
        List<ResponseTicket> tickets = ticketMapper.findByCustomer(id);
        for (ResponseTicket ticket : tickets) {
            ticket.setSeatId(ticket.getSeatId() % 200);
            if (ticket.getSeatId() == 0) ticket.setSeatId(200);
        }
        return tickets;
    }

    //회원 계정으로 티켓 조회
    @Override
    public List<ResponseTicket> findByMember(LoginDto loginDto) {
        Member member = memberMapper.findByAccount(loginDto.getAccount());
        // 아이디로 조회한 회원이 없거나 비밀 번호가 맞지 않을 경우 -> 예외 발생시키기.
        if (member == null || !passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("해당 ID와 PASSWORD가 일치하는 회원이 없음.");
        }
        List<ResponseTicket> tickets = ticketMapper.findByMember(loginDto.getAccount());
        for (ResponseTicket ticket : tickets) {
            ticket.setSeatId(ticket.getSeatId() % 200);
            if (ticket.getSeatId() == 0) ticket.setSeatId(200);
        }
        return tickets;
    }


    @Override
    public void updateState(Ticket ticket) {
        ticketMapper.updateState(ticket);
    }

    @Override
    public void deleteById(int id) {
        ticketMapper.deleteById(id);
    }

    @Transactional
    @Override
    public void createCustomerTicket(RequestCreateTicket requestCreateTicket) {
        Customer customer = new Customer();
        Ticket ticket = new Ticket();
        //System.out.println(requestCustomerTicket);

        customer.setBirthday(requestCreateTicket.getBirthday());
        customer.setNation(requestCreateTicket.getNation());
        customer.setEmail(requestCreateTicket.getEmail());
        customer.setSex(requestCreateTicket.getSex());
        customer.setPhone(requestCreateTicket.getPhone());
        customer.setEnFirstName(requestCreateTicket.getEnFirstName());
        customer.setEnLastName(requestCreateTicket.getEnLastName());
        customerMapper.create(customer);
        //id넣어서 새로 customer 갱신
        customer = customerMapper.findByEmail(customer.getEmail());
        ticket.setPayment(requestCreateTicket.getPayment());
        ticket.setCardNo(requestCreateTicket.getCardNo());
        ticket.setFlightId(requestCreateTicket.getFlightId());
        ticket.setCustomerId(customer.getId());
        ticket.setSeatId(requestCreateTicket.getSeatId());
        //티켓 생성
        ticketMapper.createByCustomer(ticket);
    }
    @Override
    public void createMemberTicket(RequestCreateTicket requestCreateTicket, String token) {
        Ticket ticket = new Ticket();
        String account = jwtService.getInfo("account", token);
        //System.out.println(requestCustomerTicket);
        Member member = memberMapper.findByAccount(account);

        // 아이디로 조회한 회원이 없거나 비밀 번호가 맞지 않을 경우 -> 예외 발생시키기.
        ticket.setMemberId(member.getId());
        ticket.setPayment(requestCreateTicket.getPayment());
        ticket.setCardNo(requestCreateTicket.getCardNo());
        ticket.setFlightId(requestCreateTicket.getFlightId());
        ticket.setSeatId(requestCreateTicket.getSeatId());

        //티켓 생성
        ticketMapper.createByMember(ticket);
    }
}
