package com.example.employeeservice.entity;

import java.util.Objects;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class AbstractEntity<ID> {

  @Id
  protected ID id;


  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }

    if (other instanceof AbstractEntity<?> that) {
      return Objects.equals(id, that.id);
    }

    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}
