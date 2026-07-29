console.log("PATTERN PILOT VERSION 29 JULY");
let purl=window.location.href;
let waitingForSubmission=false;

const observer = new MutationObserver(function(mutations){
        const currentUrl=window.location.href;

        if(purl !=currentUrl){
            purl=currentUrl;
            console.log("URL Changed:", currentUrl);

            if(currentUrl.includes("/submissions/")){
                waitingForSubmission=true;
            }



        }
        if(waitingForSubmission){
                        const submissionStatus=document.querySelector("[data-e2e-locator=submission-result]");
                        if(submissionStatus){
                            console.log("Submission Status:", submissionStatus.innerText);
                            waitingForSubmission=false;
                        }

        }

});

if(window.location.href.includes("/problems/")){

    console.log("Problem Page Detected");
    const storage =new PatternPilotStorage();

    const title=document.querySelector("div.text-title-large");

    const difficulty=
    document.querySelector(".text-difficulty-easy")||
    document.querySelector(".text-difficulty-medium")||
    document.querySelector(".text-difficulty-hard");
    const url=window.location.href

    if(title && difficulty){



        storage.getProblem(url,function(problem){
            if(problem==undefined){
                storage.saveProblem(new Problem(title.innerText, difficulty.innerText,url));
                console.log("New Problem Added");
            }
            else{
                console.log("Title: ", problem.title );
                console.log("Difficulty: ",problem.difficulty);
                console.log("URL: ",problem.url);
            }

       });
       observer.observe(document.body,{
                       childList:true,
                       subtree: true
                   });



    }



}



