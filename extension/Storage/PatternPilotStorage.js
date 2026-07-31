class PatternPilotStorage {
        saveProblem(problem){
            chrome.storage.local.set({[problem.getUrl()]: problem},
            function() {
                console.log("Problem saved successfully.");
            });

        }
        getProblem(url, callback){
            chrome.storage.local.get(url,function(result){
                const problem=result[url];
                if(!problem){
                    callback(undefined);
                    return;
                }
                callback(Problem.fromJson(problem));
            });


        }
        getAllProblems(){

        }

        deleteProblem(url){


        }
}