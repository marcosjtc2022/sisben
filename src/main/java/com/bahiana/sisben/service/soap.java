package com.bahiana.sisben.service;

import javax.xml.bind.annotation.XmlSeeAlso;

import org.springframework.beans.factory.ObjectFactory;

//@WebService(name = "feriadoService", targetNamespace = "http://service.feriado.correios.com.br/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface soap {

}
