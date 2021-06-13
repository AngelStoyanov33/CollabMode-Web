package com.nullpointerexception.cmserver.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.nullpointerexception.cmserver.model.Messages;

public interface MessagesRepository extends CrudRepository<Messages, Integer>, MessagesRepositoryExtra<Messages, Integer>{

}
