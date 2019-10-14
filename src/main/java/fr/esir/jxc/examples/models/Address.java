package fr.esir.jxc.examples.models;

import lombok.Data;

@Data
public class Address {

  String postalCode;
  String street;
  Integer streetNumber;
  String complement;

}
