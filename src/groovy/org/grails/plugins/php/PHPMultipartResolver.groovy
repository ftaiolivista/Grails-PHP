/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.grails.plugins.php

import org.springframework.web.multipart.MultipartHttpServletRequest
import org.codehaus.groovy.grails.web.multipart.*
import javax.servlet.http.*

/**
 *
 * @author vista
 */
class PHPMultipartResolver extends ContentLengthAwareCommonsMultipartResolver {
    
     boolean isMultipart(HttpServletRequest request) {
        String uri = request.requestURI
        if(uri.length()>4){
            //println "enc "+request.getCharacterEncoding()
            if(uri[-3..-1]=='php') return false
            }
        return super.isMultipart(request)
     }
     
//     String determineEncoding(HttpServletRequest request){
//        String uri = request.requestURI
//        if(uri.length()>4){
//            if(uri[-3..-1]=='php') return 'iso-8859-1'
//            }
//        return super.determineEncoding(request)
//     }
//	
//    
//     public void cleanupMultipart(MultipartHttpServletRequest request){
//        String uri = request.requestURI
//        if(uri.length()>4){
//            if(uri[-3..-1]=='php') return
//            }
//        super.cleanupMultipart(request)
//     }
    
}

