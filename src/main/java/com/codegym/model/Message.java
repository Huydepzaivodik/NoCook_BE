package com.codegym.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Message {
      @Id
      private Long id;

      private String content;
      @ManyToOne
      private User from;
      @ManyToOne
      private User to;
}
