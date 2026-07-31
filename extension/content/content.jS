function getStat(statName){
    const label=[...document.querySelectorAll("div")].find(div=>div.textContent.trim()===statName);
    if(!label)return null;
    const Details=label.parentElement.lastElementChild.innerText.split("\n");
    return `${Details[0]} ${Details[1]}`;
}
const storage =new PatternPilotStorage();




console.log("PATTERN PILOT VERSION 29 JULY");
let purl=window.location.href;
let waitingForSubmission=false;
let psid=0;



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
                        const runtime =getStat("Runtime");
                        const memory=getStat("Memory");

                        if(submissionStatus && runtime && memory ){
                        const parts=window.location.href.split('/');
                        parts.pop();
                        const sid=parts.pop()
                        parts.pop();

                        const keyurl=parts.join('/')+"/description/";

                         storage.getProblem(keyurl,function(problem){


                             problem.addSubmission(new Submission(sid,  submissionStatus.innerText , runtime,memory,new Date(), "JavaScript"));
                             storage.saveProblem(problem);
                         });



                            console.log("Submission Status:", submissionStatus.innerText);


                            waitingForSubmission=false;
                        }

        }

});

if(window.location.href.includes("/problems/")){

    console.log("Problem Page Detected");


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



