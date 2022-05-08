// メインjavascript
"use strict";

// globalを使用しない。
(function() {
    const elDetailForm = document.querySelector('#destination_detail_form');
    elDetailForm.addEventListener('submit', handleFormSubmit);

    /**
     * handleFormSubmit
     * フォームにしたがって、WishListにカードを追加
     * 
     * @param {*} event 
     */
    function handleFormSubmit(event) {
        event.preventDefault();
        // フォームから値を読み込み
        const destName = event.target.elements['name'].value;
        const destLocation = event.target.elements['location'].value;
        const destPhotoURL = event.target.elements['photo'].value;
        const destDesc = event.target.elements['description'].value;

        // フォームをクリア
        for (let i = 0; i < elDetailForm.length; i++) {
            elDetailForm.elements[i].value = "";
        }

        // wish listのタイトルを変更
        const elTitle = document.getElementById('title');
        const elWishList = document.getElementById('wishlist_container');
        if (elWishList.children.length === 0) { // カードがない
            elTitle.innerText = "My Wish List";
        }

        // 
        const elCard = createDestinationCard(destName, destLocation, destPhotoURL, destDesc);
        elWishList.appendChild(elCard);
    }

    /**
     * createDestinationCard
     * Wish Listに載せるカードのnodeを作成する
     * 
     * @param {string} title 		カードのタイトル
     * @param {string} location 	カードの観光地の場所
     * @param {string} photoURL 	カードにのせる写真のURL
     * @param {string} description 	観光地の説明
     */
    function createDestinationCard(title, location, photoURL, description) {
        const DEFAULT_PHOTO_URL = "images/signpost.jpg";

        const elCard = document.createElement('div');
        elCard.className = "card";

        const elImg = document.createElement('img');
        elImg.setAttribute('alt', title);
        if (photoURL !== "") {
            elImg.src = photoURL;
        } else {
            elImg.src = DEFAULT_PHOTO_URL;
        }

        // カード本体作成
        const elCardBody = document.createElement('div');
        elCardBody.className = "card-body";

        const elCardTitle = document.createElement('h3');
        elCardTitle.innerText = title;
        elCardBody.appendChild(elCardTitle);


        const elCardLocation = document.createElement('h4')
        elCardLocation.innerText = location;
        elCardBody.appendChild(elCardLocation);

        if (description !== "") {
            const elCardDesc = document.createElement('p');
            elCardDesc.className = "card-desc";
            elCardDesc.innerText = description;
            elCardBody.appendChild(elCardDesc);
        }

        // remove button
        const elRemoveButton = document.createElement('button');
        elRemoveButton.innerText = "remove";
        elRemoveButton.addEventListener("click", removeCard);
        elCardBody.appendChild(elRemoveButton);

        // カード 作成
        elCard.appendChild(elImg);
        elCard.appendChild(elCardBody);

        //
        return elCard;
    }

    /**
     * removeCard
     * Wish Listに登録したカードを削除する
     * 
     * @param {event event 
     */
    function removeCard(event) {
        const elCard = event.target.parentElement.parentElement;
        elCard.remove();
    }
})();
