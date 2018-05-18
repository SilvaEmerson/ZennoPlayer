function playMusic(event){

    const btn = document.getElementById(event.currentTarget.id);
    const player = btn.firstElementChild;

	if(player.paused){
        player.play();
        btn.style.color = "white";
        btn.style.backgroundColor = "black";
    }else{
        player.pause();
        btn.style.backgroundColor = "#04B486";
        btn.style.color = "black";
    }

};