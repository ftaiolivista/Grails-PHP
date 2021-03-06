import org.codehaus.groovy.grails.commons.ConfigurationHolder

class PhpGrailsPlugin {
    def version = '0.1.9',grailsVersion = "1.1 > *"
    def dependsOn = [:], loadAfter = ['h2','controllers', 'services', 'hibernate'],pluginExcludes = ["grails-app/views/error.gsp"]
    def author = "Taioli Fabiano, Mingfai Ma", authorEmail = "ftaioli@vista.it, mingfai.ma@gmail.com"
    def title = "Support PHP in your Grails application with the Quercus PHP engine"
    def description = '''\\
This plugin includes:
 - Use CleverCloud Querccus version  https://github.com/CleverCloud/Quercus
 - MySQL Connector (JDBC Driver) 5.1.13
 - Servlet configurations
 - Tested with Grails 1.3.7
 - to support UTF8 add unicode=true to init-params
'''
    

    def documentation = "http://grails.org/PHP+Plugin"

    def doWithWebDescriptor = { xml ->
      def config =  ConfigurationHolder.config.plugins.php

	  def servlets = xml.'servlet'
      servlets[servlets.size()-1] + {
        servlet{
          'servlet-name'('quercus')
          'servlet-class'('com.caucho.quercus.servlet.QuercusServlet')
          def initParams = config.'initParameters'.with{(it instanceof Map?it:it.flatten())}
          initParams.each{k,v-> 'init-param'{'param-name'(k);'param-value'(v)}}
          
          if (!(config.'loadOnStartup' instanceof ConfigObject)){
            'load-on-startup'(config.'loadOnStartup')
          }
//          'init-param'{'param-name'('unicode');'param-value'('true')} <--- this do the magic
//          'init'{
//              'php-ini'{
//                        'unicode.semantics' 'on'
//              }                    
//          }
        }
      }

      def mappings = config.mappings instanceof groovy.util.ConfigObject ? ['*.php'] : config.mappings

      def servletMappings = xml.'servlet-mapping'
      servletMappings[servletMappings.size()-1] + {
       'servlet-mapping'{
          'servlet-name'('quercus')
          mappings.each{ m ->
          'url-pattern'(m)
          }
        }
      }

      def welcomeFiles = xml.'welcome-file-list'.'welcome-file'
      welcomeFiles[0] + {
          'welcome-file'('index.php')
      }
    }

    def doWithSpring = {
        // TODO Implement runtime spring config (optional)
        multipartResolver(org.grails.plugins.php.PHPMultipartResolver)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }
    
    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }
}
