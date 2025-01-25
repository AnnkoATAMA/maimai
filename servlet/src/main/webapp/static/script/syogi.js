function highlightMove(i, j, moveData, isFoe, bord) {

	let jsonBord = JSON.parse(bord);
	let pieceMove = JSON.parse(moveData)[jsonBord[i][j]];

	if (!moveData) {
	    console.error('moveData is undefined or empty.');
	    return;
	}
		
	resetButtons();
	enablePieceClicks(jsonBord);

	enableGlobalReset(jsonBord);
	
	let possibleMoves = calculatePossibleMoves(i, j, pieceMove, isFoe, jsonBord);

    possibleMoves.forEach(move => {
        if (move[0] >= 0 && move[0] < 9 && move[1] >= 0 && move[1] < 9) {
            let targetButton = document.querySelector(`[data-i="${move[0]}"][data-j="${move[1]}"]`);
            if (targetButton) {
                targetButton.style.backgroundColor = 'red';
				
				targetButton.onclick = (e) =>{
					e.stopPropagation();
					jsonBord = execute(i, j, move[0], move[1],jsonBord);
				}
            }
        }
    });
}

function updatePieceDisplay(button, piece) {
    const pieceNames = {
        1: '歩', 11: 'と',
        2: '香', 12: '杏',
        3: '桂', 13: '圭',
        4: '銀', 14: '全',
        5: '金',
        6: '角', 16: '馬',
        7: '飛', 17: '竜',
        8: '玉'
    };

    button.textContent = pieceNames[piece] || '';
}

function updatePiecePlace(button, piece) {
    const pieceNames = {
        1: '歩',
        2: '香',
        3: '桂',
        4: '銀',
        5: '金',
        6: '角',
        7: '飛',
        8: '玉'
    };

    button.textContent = pieceNames[piece] + button.getAtribute("bord")[button.getAttribute("isFoe") - 1][piece] || '';
}

function promotePiece(i,j,isFoe,piece){
	let candidate = [1,2,3,4,6,7];
	if(isFoe == 1 && 0 <= i && i < 3 && candidate.includes(piece)){
		if (confirm("この駒を成りますか？")) {
		    return piece + 10;
		} 
	}if(isFoe == 2 && 6 <= i && i < 9 && candidate.includes(piece)){
		if (confirm("この駒を成りますか？")) {
		    return piece + 10;
		}		
	}
	
	return piece;
}

function execute(fromI, fromJ, toI, toJ, bord){

	let fromButton = document.querySelector(`[data-i="${fromI}"][data-j="${fromJ}"]`);
	let toButton = document.querySelector(`[data-i="${toI}"][data-j="${toJ}"]`);

	let piece = bord[fromI][fromJ];
	let isFoe = bord[fromI + 11][fromJ];
	
	bord[fromI].splice(fromJ,1,0);
	bord[fromI + 11].splice(fromJ,1,0);
	
	if(isFoe == 1){
		bord[9].splice(piece,1,bord[9][piece]+1);
	
	} else if(isFoe == 2){
		bord[10].splice(piece,1,bord[10][piece]+1);
		
	}
	
	piece = promotePiece(toI,toJ,isFoe,piece);
	bord[toI].splice(toJ,1,piece);
	bord[toI + 11].splice(toJ,1,isFoe);
	
	if (fromButton && toButton) {
        updatePieceDisplay(toButton, piece);
        toButton.style.transform = fromButton.style.transform;

        fromButton.textContent = '';
        fromButton.style.transform = '';
        fromButton.style.backgroundColor = 'rgb(230, 201, 162)';
    }
	
	resetButtons();
	
	enablePieceClicks(bord);
	return bord;
}

// ボタンのリセット
function resetButtons() {
    let buttons = document.querySelectorAll('.piece');
    buttons.forEach(button => {
        button.style.backgroundColor = 'rgb(230, 201, 162)';
        button.onclick = null;
    });
}

function enableGlobalReset(bord){
	document.removeEventListener('click', handleGlobalClick);
	
	document.addEventListener('click', (event) => handleGlobalClick(event, bord));
}

function handleGlobalClick(event,bord){
	const clickedElement = event.target;
	
	if(!clickedElement.classList.contains('piece') && !clickedElement.closest('.bord')){
		resetButtons();
		enablePieceClicks(bord);
	}
}
function initializeBoard(bord) {
    let buttons = document.querySelectorAll('.piece');
    buttons.forEach(button => {
        let i = parseInt(button.dataset.i);
        let j = parseInt(button.dataset.j);
        let piece = bord[i][j];

        updatePieceDisplay(button, piece);
    });
}
function enablePieceClicks(bord) {
    let buttons = document.querySelectorAll('.piece');
    buttons.forEach(button => {
        button.onclick = (e) => {
			e.stopPropagation();
            let i = parseInt(button.dataset.i);
            let j = parseInt(button.dataset.j);
            let moveData = button.getAttribute('data-move');
            let isFoe = bord[i + 11][j];
			let bordData = JSON.stringify(bord);
            highlightMove(i, j, moveData, isFoe, bordData);
        };
    });
}

function calculatePossibleMoves(i, j, moveData, isFoe, bord) {
    let possibleMoves = [];

	if(isFoe == 1){
	    moveData.forEach((row, rowIndex) => {
	        row.forEach((value, colIndex) => {
	            let targetI = i + (rowIndex - 2);
	            let targetJ = j + (colIndex - 1);
	
	            if (targetI >= 0 && targetI < 9 && targetJ >= 0 && targetJ < 9) {
					if(value == 1){
		                if (isValidMove(bord[targetI + 11][targetJ], bord[i + 11][j])) {
		                    possibleMoves.push([targetI, targetJ]);
		                }
						
					} else if(value == 2){
						if(bord[i][j] == 6 || bord[i][j] == 16){
							possibleMoves = goDiagonally(possibleMoves, bord, i, j, targetI, targetJ, isFoe);
						} else {
							possibleMoves = muchProgress(possibleMoves, bord, i, j, targetI, targetJ, isFoe);
						}
					}
	            }
	        });
	    });
		
	} else if(isFoe == 2){
		moveData.reverse();
		moveData.forEach((row, rowIndex) => {
		        row.forEach((value, colIndex) => {
		            let targetI = i + (rowIndex - 1);
		            let targetJ = j + (colIndex - 1);

		            if (targetI >= 0 && targetI < 9 && targetJ >= 0 && targetJ < 9) {
						if(value == 1){
			                if (isValidMove(bord[targetI + 11][targetJ], bord[i + 11][j])) {
			                    possibleMoves.push([targetI, targetJ]);
			                }
						} else if(value == 2){
							if(bord[i][j] == 6 || bord[i][j] == 16){
								possibleMoves = goDiagonally(possibleMoves, bord, i, j, targetI, targetJ, isFoe);
							} else {
								possibleMoves = muchProgress(possibleMoves, bord, i, j, targetI, targetJ, isFoe);
							}
						}
		            }
		        });
		    });
		
	}
	
	
    return possibleMoves;
}

function goDiagonally(possibleMoves, bord, i, j, targetI, targetJ, isFoe){
	
	console.log(possibleMoves);
	
	let foe = 2;
	if(foe == isFoe){
		foe = 1;
	}
	
	let upDownI = targetI - i;
	let upDownJ = targetJ - j;
	
	while(0 <= targetI && targetI < 9 && 0 <= targetJ && 
		targetJ < 9 && bord[targetI + 11][targetJ] != isFoe && bord[targetI + 11][targetJ] != foe){
		
		possibleMoves.push([targetI,targetJ]);
		
		targetI += upDownI;
		targetJ += upDownJ;
	}
	
	if(0 <= targetI && targetI < 9 && 0 <= targetJ && 
			targetJ < 9 && bord[targetI + 11][targetJ] == foe ){
		possibleMoves.push([targetI,targetJ]);
	}
	
    return possibleMoves;
}

function muchProgress(possibleMoves, bord, i, j, targetI, targetJ, isFoe){

	let foe = 2;
	if(foe == isFoe){
		foe = 1;
	}
	
	let upDwon = 0;
	let height = true;	
	if(targetI == i){
		height = false;
		upDwon = targetJ -j;
	}else if(targetJ == j){
		height = true;
		upDwon = targetI - i;
	}
		
	while(0 <= targetI && targetI < 9 && 0 <= targetJ && 
		targetJ < 9 && bord[targetI + 11][targetJ] != isFoe && bord[targetI + 11][targetJ] != foe){
		
		console.log([targetI,targetJ]);
		possibleMoves.push([targetI,targetJ]);
		
		if(height){
			targetI += upDwon;
		} else {
			targetJ += upDwon;
			
		}
	}
	
	if(bord[targetI + 11][targetJ] == foe){
		possibleMoves.push([targetI,targetJ]);
	}
	
	
	return possibleMoves;
}

function isValidMove(isFoe,myself){ return isFoe === 0 || isFoe !== myself; }