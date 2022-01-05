package com.frabbi.londriservice.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AddressModel implements Serializable {
    private String mapLocation;
    private String homeAddress;
    private String phoneNumber;
}
