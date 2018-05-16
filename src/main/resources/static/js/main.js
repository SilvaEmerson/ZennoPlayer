// const player = document.getElementById("player");
// const player = document.getElementsByClassName("player")[0]
// const btn = document.getElementsByClassName("button")[0];

function playMusic(event){

    const btn = document.getElementById(event.currentTarget.id);
    const player = btn.firstElementChild;

    console.log(btn);
    console.log(player);
    console.log("Hello");
	// btn.classList.toggle("paused");
	// console.log(player.audioTracks.length);

	if(player.paused){
        player.play();
        btn.style.color = "#04B486";
        btn.style.backgroundColor = "black";
    }else{
        player.pause();
        btn.style.backgroundColor = "#04B486";
        btn.style.color = "black";
    }

};

// const toggleMusicSrc = (musciSrc) => {
//     player.src = musciSrc;
// };