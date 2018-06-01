/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer;

/**
 * The purpose of the exception is to make sure the customer does not enter
 * shed-dimensions bigger than the carport-dimensions
 *
 * @author s_ele
 */
public class SubmitOrderException extends Exception
{

    public SubmitOrderException(String message)
    {
        super(message);
    }

}
