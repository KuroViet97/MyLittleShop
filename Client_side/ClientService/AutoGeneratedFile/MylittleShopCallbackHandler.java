
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

        
           /**
            * auto generated Axis2 call back method for checkProduct method
            * override this method for handling normal response from checkProduct operation
            */
           public void receiveResultcheckProduct(
                    com.product.service.MylittleShopStub.CheckProductResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from checkProduct operation
           */
            public void receiveErrorcheckProduct(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for viewTransaction method
            * override this method for handling normal response from viewTransaction operation
            */
           public void receiveResultviewTransaction(
                    com.product.service.MylittleShopStub.ViewTransactionResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from viewTransaction operation
           */
            public void receiveErrorviewTransaction(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for getAllProductBarcodes method
            * override this method for handling normal response from getAllProductBarcodes operation
            */
           public void receiveResultgetAllProductBarcodes(
                    com.product.service.MylittleShopStub.GetAllProductBarcodesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAllProductBarcodes operation
           */
            public void receiveErrorgetAllProductBarcodes(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getData method
            * override this method for handling normal response from getData operation
            */
           public void receiveResultgetData(
                    com.product.service.MylittleShopStub.GetDataResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getData operation
           */
            public void receiveErrorgetData(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for validateInfoEmployee method
            * override this method for handling normal response from validateInfoEmployee operation
            */
           public void receiveResultvalidateInfoEmployee(
                    com.product.service.MylittleShopStub.ValidateInfoEmployeeResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from validateInfoEmployee operation
           */
            public void receiveErrorvalidateInfoEmployee(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for validateInfo method
            * override this method for handling normal response from validateInfo operation
            */
           public void receiveResultvalidateInfo(
                    com.product.service.MylittleShopStub.ValidateInfoResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from validateInfo operation
           */
            public void receiveErrorvalidateInfo(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for getShopItemQuantity method
            * override this method for handling normal response from getShopItemQuantity operation
            */
           public void receiveResultgetShopItemQuantity(
                    com.product.service.MylittleShopStub.GetShopItemQuantityResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getShopItemQuantity operation
           */
            public void receiveErrorgetShopItemQuantity(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for comparetransactions method
            * override this method for handling normal response from comparetransactions operation
            */
           public void receiveResultcomparetransactions(
                    com.product.service.MylittleShopStub.ComparetransactionsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from comparetransactions operation
           */
            public void receiveErrorcomparetransactions(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getPrice method
            * override this method for handling normal response from getPrice operation
            */
           public void receiveResultgetPrice(
                    com.product.service.MylittleShopStub.GetPriceResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getPrice operation
           */
            public void receiveErrorgetPrice(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAllProductNames method
            * override this method for handling normal response from getAllProductNames operation
            */
           public void receiveResultgetAllProductNames(
                    com.product.service.MylittleShopStub.GetAllProductNamesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAllProductNames operation
           */
            public void receiveErrorgetAllProductNames(java.lang.Exception e) {
            }
                
               // No methods generated for meps other than in-out
                
           /**
            * auto generated Axis2 call back method for getAllShopNames method
            * override this method for handling normal response from getAllShopNames operation
            */
           public void receiveResultgetAllShopNames(
                    com.product.service.MylittleShopStub.GetAllShopNamesResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAllShopNames operation
           */
            public void receiveErrorgetAllShopNames(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getAllShopIDs method
            * override this method for handling normal response from getAllShopIDs operation
            */
           public void receiveResultgetAllShopIDs(
                    com.product.service.MylittleShopStub.GetAllShopIDsResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getAllShopIDs operation
           */
            public void receiveErrorgetAllShopIDs(java.lang.Exception e) {
            }
                


    }
    