console.log('DETTAGLIO JS OK!')

const URLParams = new URLSearchParams(window.location.search);
const photoId = URLParams.get('id');

// let usersList;

// const usersPromise = new Promise((resolve, reject) => {
//     resolve(userList());
// })

const commentTextArea = document.getElementById('commentAdd');

const commentBtn = document.getElementById('commentBtn');
console.log(commentBtn)

commentBtn.addEventListener("click", function (event) {
    createComment(event);
});

// usersPromise.then(photoDetail());

photoDetail();

function photoDetail() {

    axios.get(`http://localhost:8080/api/photos/${photoId}`)
        .then((result) => {

            // console.log('lista utenti', usersList)

            const photo = result.data;
            console.log("Photo", photo);

            const tags = photo.tags;
            const categories = photo.categories;
            const comments = photo.comments;

            document.getElementById('photoTitle').innerHTML = photo.title;

            document.getElementById('photoDetails').innerHTML += `
            <img class="img-thumbnail m-auto" src="${photo.url}" alt="foto_${photo.id}" style="min-height: 200px; min-width: 200px">
            
            <!-- Descrizione della foto -->
            <p class="py-3 fs-3">${photo.description}</p>

            <!-- Se tag non è vuoto -->
            <div class="tags">
                <h4>Tag:</h4>
                <ul class="tags-list list-unstyled d-flex flex-wrap gap-1"></ul>
            </div>

            <!-- Se categorie non è vuoto -->
            <div class="categories">
                <h4>Categorie:</h4>
                <ul class="categories-list list-unstyled d-flex flex-wrap gap-1"></ul>
            </div>

            <!-- Se commenti non è vuoto -->
            <div class="comments">
                <ul class="comments-list list-unstyled d-flex flex-column flex-wrap gap-1"></ul>
            </div>
            `;

            tags.forEach(tag => {
                document.querySelector('.tags-list').innerHTML += `
                    <li>
                        <a class="text-warning text-decoration-none"
                            href="#">${tag.name}</a>
                    </li>`;
            });

            categories.forEach(category => {
                document.querySelector('.categories-list').innerHTML += `
                    <li>
                        <a class="text-info text-decoration-none"
                            href="#">${category.name}</a>
                    </li>`;
            });

            comments.forEach(comment => {
                document.querySelector('.comments-list').innerHTML += `
                    <li>
                        <span id="comment_${comment.id}" class="username fw-bold text-primary">Nome utente:</span> ${comment.content}
                    </li>`;

                // // Itero i commenti di ogni utente per poter assegnare lo username corretto ai commenti
                // usersList.forEach(user => {
                //     // console.log('utente per username', user)

                //     user.comments.forEach(userComment => {
                //         // console.log('id dei commenti dell utente', userComment.id)
                //         if (userComment.id == comment.id) {
                //             console.log('id del commento', comment.id)
                //             document.querySelector(`#comment_${comment.id}`).innerHTML = `${user.username}`;
                //         }
                //     });


                // })

            });


        }).catch((result) => {
            console.error("Errore nella richiesta", result);
            alert("Errore durante la richiesta")
        })

}

// function userList() {

//     axios.get(`http://localhost:8080/api/photos/${photoId}/comments/users`)
//         .then((result) => {
//             console.log(result.data);
//             usersList = result.data;
//         }).catch((result) => {
//             console.error('Errore nella richiesta', result);
//             alert('Errore durante la richiesta')
//         })

// }

function createComment(event) {

    console.log('cliccato', event);

    event.preventDefault();

    const comment = {
        content: commentTextArea.value
    }

    console.log("Commento: ", comment);

    axios.post(`http://localhost:8080/api/photos/${photoId}/comments`, comment)
        .then((result) => {
            console.log("Inserimento dati di", comment)
            window.location.replace(`/photo?id=${photoId}`);
        }).catch((result) => {
            console.error("Errore nella richiesta", result);
            alert("Errore durante la richiesta")
        })

}