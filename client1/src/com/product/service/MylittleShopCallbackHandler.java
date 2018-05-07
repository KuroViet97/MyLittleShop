
/**
 * MylittleShopCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.7  Built on : Nov 20, 2017 (11:41:20 GMT)
 */

    package com.product.service;

    /**
     *  MylittleShopCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class MylittleShopCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public MylittleShopCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public MylittleShopCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
               // No methods generated for meps other than in-out
                
               // No methods generated for meps other than in-out
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for getData3 method
            * override this method for handling normal response from getData3 operation
            */
           public void receiveResultgetData3(
                    com.product.service.MylittleShopStub.GetData3Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getData3 operation
           */
            public void receiveErrorgetData3(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getData2 method
            * override this method for handling normal response from getData2 operation
            */
           public void receiveResultgetData2(
                    com.product.service.MylittleShopStub.GetData2Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getData2 operation
           */
            public void receiveErrorgetData2(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getData1 method
            * override this method for handling normal response from getData1 operation
            */
           public void receiveResultgetData1(
                    com.product.service.MylittleShopStub.GetData1Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getData1 operation
           */
            public void receiveErrorgetData1(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for viewDatabase1 method
            * override this method for handling normal response from viewDatabase1 operation
            */
           public void receiveResultviewDatabase1(
                    com.product.service.MylittleShopStub.ViewDatabase1Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from viewDatabase1 operation
           */
            public void receiveErrorviewDatabase1(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for viewDatabase2 method
            * override this method for handling normal response from viewDatabase2 operation
            */
           public void receiveResultviewDatabase2(
                    com.product.service.MylittleShopStub.ViewDatabase2Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from viewDatabase2 operation
           */
            public void receiveErrorviewDatabase2(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for viewDatabase3 method
            * override this method for handling normal response from viewDatabase3 operation
            */
           public void receiveResultviewDatabase3(
                    com.product.service.MylittleShopStub.ViewDatabase3Response result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from viewDatabase3 operation
           */
            public void receiveErrorviewDatabase3(java.lang.Exception e) {
            }
                


    }
    