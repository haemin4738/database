package com.koreatech.byeongcheonairlineapi.service.impl;

import com.koreatech.byeongcheonairlineapi.dto.LoginDto;
import com.koreatech.byeongcheonairlineapi.dto.Model.ResponseTicket;
import com.koreatech.byeongcheonairlineapi.dto.domain.Member;
import com.koreatech.byeongcheonairlineapi.dto.domain.Ticket;
import com.koreatech.byeongcheonairlineapi.mapper.MemberMapper;
import com.koreatech.byeongcheonairlineapi.mapper.TicketMapper;
import com.koreatech.byeongcheonairlineapi.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final TicketMapper ticketMapper;
    private final MemberMapper memberMapper;
    @Autowired
    public TicketServiceImpl(TicketMapper ticketMapper, MemberMapper memberMapper) {
        this.ticketMapper = ticketMapper;
        this.memberMapper = memberMapper;
    }

    @Override
    public List<Ticket> findAll() {
        return ticketMapper.findAll();
    }

    //비회원 id로 티켓 조회
    @Override
    public List<ResponseTicket> findByCustomer(int id) {
        return ticketMapper.findByCustomer(id);
    }

    //회원 계정으로 티켓 조회
    @Override
    public List<ResponseTicket> findByMember(LoginDto loginDto) {
        Member member = memberMapper.findByAccount(loginDto.getAccount());
        // 아이디로 조회한 회원이 없거나 비밀 번호가 맞지 않을 경우 -> 예외 발생시키기.
        if (member == null || !passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("해당 ID와 PASSWORD가 일치하는 회원이 없음.");
        }
        return ticketMapper.findByMember(loginDto.getAccount());
    }


    //ticket 데이터 넣기
    @Override
    public void insert(Ticket ticket) {
        ticketMapper.insert(ticket);
    }

    @Override
    public void updateState(Ticket ticket) {
        ticketMapper.updateState(ticket);
    }

    @Override
    public void deleteById(int id) {
        ticketMapper.deleteById(id);
    }


}
