if(window.location.href.includes("/problems/")){
    console.log("Problem Page Detected");

    const title=document.querySelector("div.text-title-large");

    const difficulty=
    document.querySelector(".text-difficulty-easy")||
    document.querySelector(".text-difficulty-medium")||
    document.querySelector(".text-difficulty-hard");

    if(title && difficulty){
    console.log("Title: ",title.innerText );
    console.log("Difficulty: ",difficulty.innerText)

    }
    console.log("URL: ",window.location.href);
}



