console.log('DETTAGLIO JS OK!')

const URLParams = new URLSearchParams(window.location.search);
const photoId = URLParams.get('id');

photoDetail();

function photoDetail() {

    axios.get(`http://localhost:8080/api/photos/${photoId}`)
        .then((result) => {

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
                console.log('commento', comment)

                document.querySelector('.comments-list').innerHTML += `
                    <li>
                        <span class="fw-bold text-primary">${comment.username}:</span> ${comment.content}
                    </li>`;
            });

        }).catch((result) => {
            console.error("Errore nella richiesta", result);
            alert("Errore durante la richiesta")
        })

}