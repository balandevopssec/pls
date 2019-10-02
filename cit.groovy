node("$NodeName"){

    wrks = env.WORKSPACE
    stage("Prepare"){
        println("Preparing...")
        git(
            url: "git@github.com:balandevopssec/pls.git",
            branch: "master"
        )
        dir('config'){
            git(
                url: "git@github.com:balandevopssec/conf.git",
                branch: "master"
            )
        }
        println("Prepared.")
    }
    stage("Cloning"){
        println("Cloning the application repo...")
        load 'app/clone.groovy'
        println("Cloned the App Repo.")
    }
    stage("Build"){
        println("Building the App...")
        load 'app/build.groovy'
        println("Builded the App.")
    }
    stage("Deploy"){
        println("Deploying the Application...")
        sh "chmod +x scripts/dep.sh"
        sh "scripts/dep.sh"
        println("Deployed the App..!")
    }
    
}