package com.sangguy.restbbs.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 100, message = "2자 이상 100자 이하로 제목을 입력해주세요.")
    private String title;

    @NotNull
    @Size(min = 2, max = 1000, message = "2자 이상 1000자 이하로 내용을 입력해주세요.")
    private String content;
}
