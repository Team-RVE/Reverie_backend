package com.example.rve.domain.post.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Builder.Default
  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "post_id")
  private List<Image> images = new ArrayList<>();

  private String title;

  private String content;

  private Date createdAt;

  private Category category;

  @Builder.Default
  @Column(name = "`like`", nullable = false)
  private boolean like = false;

  public void addImages(Image image) {
    images.add(image);
  }
}
