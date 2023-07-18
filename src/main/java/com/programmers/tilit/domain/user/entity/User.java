package com.programmers.tilit.domain.user.entity;

import javax.persistence.Entity;

import com.programmers.tilit.global.common.BaseEntity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {
}
