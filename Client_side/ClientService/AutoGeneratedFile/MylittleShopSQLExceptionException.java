
/**
 * MylittleShopSQLExceptionException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */

package com.product.service;

public class MylittleShopSQLExceptionException extends java.lang.Exception{

    private static final long serialVersionUID = 1525940239922L;
    
    private com.product.service.MylittleShopStub.MylittleShopSQLException faultMessage;

    
        public MylittleShopSQLExceptionException() {
            super("MylittleShopSQLExceptionException");
        }

        public MylittleShopSQLExceptionException(java.lang.String s) {
           super(s);
        }

        public MylittleShopSQLExceptionException(java.lang.String s, java.lang.Throwable ex) {
          super(s, ex);
        }

        public MylittleShopSQLExceptionException(java.lang.Throwable cause) {
            super(cause);
        }
    

    public void setFaultMessage(com.product.service.MylittleShopStub.MylittleShopSQLException msg){
       faultMessage = msg;
    }
    
    public com.product.service.MylittleShopStub.MylittleShopSQLException getFaultMessage(){
       return faultMessage;
    }
}
    