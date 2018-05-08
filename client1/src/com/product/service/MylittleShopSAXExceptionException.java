
/**
 * MylittleShopSAXExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */

package com.product.service;

public class MylittleShopSAXExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1525745256653L;
    
    private com.product.service.MylittleShopStub.MylittleShopSAXException faultMessage;

    
        public MylittleShopSAXExceptionException() {
            super("MylittleShopSAXExceptionException");
        }

        public MylittleShopSAXExceptionException(java.lang.String s) {
           super(s);
        }

        public MylittleShopSAXExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public MylittleShopSAXExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.product.service.MylittleShopStub.MylittleShopSAXException msg){
       faultMessage = msg;
    }
    
    public com.product.service.MylittleShopStub.MylittleShopSAXException getFaultMessage(){
       return faultMessage;
    }
}
    