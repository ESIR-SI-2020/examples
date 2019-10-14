package fr.esir.jxc.examples.common.models;

import lombok.Data;

@Data
public class Address {

  String postalCode;
  String street;
  Integer streetNumber;
  String complement;

}
