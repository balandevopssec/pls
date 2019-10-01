folder('$BUName'){}
folder('$BUName/$ProductName'){}
pipelineJob('$BUName/$ProductName/CICD_$AppName'){
    parameters{
        stringParam("$ApplicationRepo","AppRepo","Git URL")
        stringParam("$AppName", "AppName", "Application Name")
        stringParam("$UnitTestRun", "UnitTestTool", "")
        stringParam("$NodeName", "NodeName", "")

        activeChoiceParam('Branch'){
            description('Select the Branch')
            filterable()
            choiceType('SINGLE_SELECT')
            groovyScript{
                script('"master", "develop"')
                fallbackScript('"Fallback choice"')
            }
        }
    }

    definition{
        cps{
            def jobScript = readFileFromWorkspace('cit.groovy')
            script(jobScript)
            def approvals = org.jenkinsci.plugins.scriptsecurity.scripts.ScriptApproval.get()
            approvals.approveScript(approvals.hash(jobScript,"groovy"))
        }
    }
}