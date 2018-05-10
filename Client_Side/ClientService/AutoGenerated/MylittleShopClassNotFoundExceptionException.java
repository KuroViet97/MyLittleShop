
/**
 * MylittleShopClassNotFoundExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */

package com.product.service;

public class MylittleShopClassNotFoundExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1525940239813L;
    
    private com.product.service.MylittleShopStub.MylittleShopClassNotFoundException faultMessage;

    
        public MylittleShopClassNotFoundExceptionException() {
            super("MylittleShopClassNotFoundExceptionException");
        }

        public MylittleShopClassNotFoundExceptionException(java.lang.String s) {
           super(s);
        }

        public MylittleShopClassNotFoundExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public MylittleShopClassNotFoundExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.product.service.MylittleShopStub.MylittleShopClassNotFoundException msg){
       faultMessage = msg;
    }
    
    public com.product.service.MylittleShopStub.MylittleShopClassNotFoundException getFaultMessage(){
       return faultMessage;
    }
}
    