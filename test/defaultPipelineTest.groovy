import testSupport.PipelineSpockTestBase
import module_Artifact
import module_Notification

class defaultPipelineTest extends PipelineSpockTestBase {
 
    def script

    def mavenMock
    def artifactMock
    def notificationMock

    def setup() {
        registerMocks()
        registerPluginMethods()
        script = loadScript('vars/defaultPipeline.groovy')
    }

    def cleanup() {
        printCallStack()
    }
 
    void 'Happy flow'() {
        when:
        script.call([:])
 
        then:
        assertJobStatusSuccess()
 
    }

    // Mock maven function
    def registerMocks() {
        mavenMock = Mock(Closure)
        helper.registerAllowedMethod('module_Maven', [String.class], mavenMock)

        // artifactMock = Mock(Closure)
        // helper.registerAllowedMethod('module_Artifact', [], artifactMock)

        // notificationMock = Mock(Closure)
        // helper.registerAllowedMethod('module_Notification', [], notificationMock)

        artifactMock = Mock(module_Artifact)
        binding.setVariable('module_Artifact', artifactMock)

        notificationMock = Mock(module_Notification)
        binding.setVariable('module_Notification', notificationMock)
    }

    // Mock junit
    def registerPluginMethods() {
        helper.registerAllowedMethod('junit', [HashMap.class], null)
    }

}