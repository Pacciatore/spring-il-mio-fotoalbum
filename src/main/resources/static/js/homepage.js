console.log('JS OK!');

photosList();

function photosList() {
    let loading = true;
    document.getElementById('photosContainer').innerHTML = `
        <div class="spinner-border text-light mt-5" role="status">
             <span class="visually-hidden">Loading...</span>
        </div>`;

    axios.get('http://localhost:8080/api/photos')
        .then((result) => {
            loading = false;

            document.getElementById('photosContainer').innerHTML = "";

            const photosList = result.data;
            console.log("Elenco foto", photosList);

            photosList.forEach(photo => {

                if (!photo.visible) {
                    return;
                }

                const tags = photo.tags;
                const categories = photo.categories;

                document.getElementById('photosContainer').innerHTML += `
                    <div class="card position-relative" style="min-height: 200px; min-width: 200px">

                        <!-- Immagine della foto -->
                        <img class="card-img" src="${photo.url}" alt="foto_${photo.id}">

                        <a href="./photo?id=${photo.id}" class="stretched-link"></a>

                        <!-- Contenuto della card -->
                        <div class="card-img-overlay">
                            <!-- Titolo della foto -->
                            <h5 class="card-title">${photo.title}</h5>
    
                            <!-- Descrizione della foto -->
                            <p class="card-text text-truncate">${photo.description}</p>
    
                            <!-- Se commenti non è vuoto -->
                            <p class="text-secondary m-0">Commenti: ${photo.comments.length}</p>
    
                            <!-- Se tag non è vuoto -->
                            <ul id="tagsList_${photo.id}" class="list-unstyled d-flex flex-wrap gap-1 text-warning mb-0">
                                
                            </ul>
    
                            <!-- Se categorie non è vuoto -->
                            <ul id="categoriesList_${photo.id}" class="list-unstyled d-flex flex-wrap gap-1 text-info mb-0">
                                
                            </ul>

                        </div>  

                    </div>`;

                tags.forEach(tag => {
                    document.getElementById(`tagsList_${photo.id}`).innerHTML += `
                    <li>${tag.name}</li>
                    `;
                })

                categories.forEach(category => {
                    document.getElementById(`categoriesList_${photo.id}`).innerHTML += `
                    <li>${category.name}</li>
                    `;
                })


            });

        }).catch((result) => {
            loading = false;

            console.error("Errore nella richiesta", result);
            alert("Errore durante la richiesta!")
        })
}