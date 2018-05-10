
/**
 * MylittleShopIOExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */

package com.product.service;

public class MylittleShopIOExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1525940239953L;
    
    private com.product.service.MylittleShopStub.MylittleShopIOException faultMessage;

    
        public MylittleShopIOExceptionException() {
            super("MylittleShopIOExceptionException");
        }

        public MylittleShopIOExceptionException(java.lang.String s) {
           super(s);
        }

        public MylittleShopIOExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public MylittleShopIOExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.product.service.MylittleShopStub.MylittleShopIOException msg){
       faultMessage = msg;
    }
    
    public com.product.service.MylittleShopStub.MylittleShopIOException getFaultMessage(){
       return faultMessage;
    }
}
    