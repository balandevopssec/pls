node("$NodeName"){

    def wrks = env.WORKSPACE
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
}