package com.rohitPal.local._business_directory.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

public class MxEmailValidator implements ConstraintValidator<ValidMxEmail , String> {

    @Override
    public void initialize(ValidMxEmail constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        if(email == null || email.isEmpty()){
            return true; //because @NotBlank will handle it .
        }
        int atIndex = email.lastIndexOf("@");
        if(atIndex < 0 || email.length()-1 == atIndex){
            return false;
        }
        String domain  = email.substring(atIndex + 1);

        return hasMXRecord(domain , constraintValidatorContext);

    }

     private boolean hasMXRecord(String domain , ConstraintValidatorContext constraintValidatorContext){


            Hashtable<String, String> env = new Hashtable<>();
            /*
            this is helpful for getting the right source to during the lookup .
             */
            env.put("java.naming.factory.initial" , "com.sun.jndi.dns.DnsContextFactory");

            env.put("com.sun.jndi.dns.timeout.initial" , "2000");
            env.put("com.sun.jndi.dns.timeout.retries" , "1");

            DirContext context = null ;
           /*
           this line is responsible for loading DNS driver into memory
            */
         try{
             context = new InitialDirContext(env);
            Attributes attributes = context.getAttributes(domain , new String [] {"MX"});
            Attribute attribute = attributes.get("MX");

            return attribute!=null && attribute.size() > 0 ;
        }catch (NameNotFoundException e){
            /*
            if any error occurs then validation get failed!!
             */
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Domain Name not found")
                    .addConstraintViolation();
            return false;
        }catch(Exception e){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Unable to " +
                    "to verify your email , please try again later").addConstraintViolation();
            return false;
        }finally{
            if(context!= null){
                try{
                    context.close();
                }catch(NamingException e){
                   /*
                   Here , we are not putting any error message over here , beacuse we want to swallow the
                   error so that the User will not be affected .
                    */
                }
            }
        }

     }
}
