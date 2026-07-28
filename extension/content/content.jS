

if(window.location.href.includes("/problems/")){
    console.log("Problem Page Detected");
    const storage =new StorageManager();

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



    }



}



