import Problem.js

if(window.location.href.includes("/problems/")){
    console.log("Problem Page Detected");

    const title=document.querySelector("div.text-title-large");

    const difficulty=
    document.querySelector(".text-difficulty-easy")||
    document.querySelector(".text-difficulty-medium")||
    document.querySelector(".text-difficulty-hard");
    let p=null;
    if(title && difficulty){
        const  p = new Problem(title.innerText, difficulty.innerText, window.location.href);

        console.log("Title: ", p.getTitle() );
        console.log("Difficulty: ",p.getDifficulty())
    }


    console.log("URL: ",p.getUrl());
}



