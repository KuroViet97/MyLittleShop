
/**
 * MylittleShopParserConfigurationExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */

package com.product.service;

public class MylittleShopParserConfigurationExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1525510984000L;
    
    private com.product.service.MylittleShopStub.MylittleShopParserConfigurationException faultMessage;

    
        public MylittleShopParserConfigurationExceptionException() {
            super("MylittleShopParserConfigurationExceptionException");
        }

        public MylittleShopParserConfigurationExceptionException(java.lang.String s) {
           super(s);
        }

        public MylittleShopParserConfigurationExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public MylittleShopParserConfigurationExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.product.service.MylittleShopStub.MylittleShopParserConfigurationException msg){
       faultMessage = msg;
    }
    
    public com.product.service.MylittleShopStub.MylittleShopParserConfigurationException getFaultMessage(){
       return faultMessage;
    }
}
    