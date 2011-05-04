import org.codehaus.groovy.grails.commons.ConfigurationHolder

class PhpGrailsPlugin {
    def version = '0.1.6-vista',grailsVersion = "1.1 > *"
    def dependsOn = [:], loadAfter = ['h2'],pluginExcludes = ["grails-app/views/error.gsp"]
    def author = "Mingfai Ma, Taioli Fabiano", authorEmail = "mingfai.ma@gmail.com, ftaioli@vista.it"
    def title = "Support PHP in your Grails application with the Quercus PHP engine"
    def description = '''\\
This plugin includes:
 - Quercus PHP Engine (http://quercus.caucho.com/) 3.2.1 - patched to avoid some error messages
 - MySQL Connector (JDBC Driver) 3.1.14
 - Servlet configurations
Tested with Grails 1.1
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
