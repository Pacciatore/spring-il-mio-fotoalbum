console.log('JS OK!');

photosList();
filtersList();

const searchBar = document.getElementById('photoQuery')

searchBar.addEventListener("keypress", function (event) {
    if (event.key === "Enter") {
        event.preventDefault();
        console.log("Ricerca", searchBar.value);
        photosListQuery(searchBar.value, 'searchBar');
    }
});

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

function photosListQuery(queryParam, filterType) {
    let loading = true;
    document.getElementById('photosContainer').innerHTML = `
        <div class="spinner-border text-light mt-5" role="status">
             <span class="visually-hidden">Loading...</span>
        </div>`;

    if (filterType === 'searchBar') {
        axios.get(`http://localhost:8080/api/photos?photo=${queryParam}`)
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
    } else if (filterType === 'tags') {
        console.log('ricerca per tags');
        axios.get(`http://localhost:8080/api/photos`)
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

                    tags.forEach(tag => {

                        if (tag.id == Number.parseInt(queryParam.id)) {

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

                        }
                    });

                });

            }).catch((result) => {
                loading = false;

                console.error("Errore nella richiesta", result);
                alert("Errore durante la richiesta!")
            })
    } else if (filterType === 'categories') {
        console.log('ricerca per categories');

        axios.get(`http://localhost:8080/api/photos`)
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

                    categories.forEach(category => {

                        if (category.id == Number.parseInt(queryParam.id)) {

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

                        }
                    });

                });

            }).catch((result) => {
                loading = false;

                console.error("Errore nella richiesta", result);
                alert("Errore durante la richiesta!")
            })

    }

}

function filtersList() {

    axios.get('http://localhost:8080/api/tags')
        .then((result) => {
            const tags = result.data;
            console.log("tags", tags);

            tags.forEach(tag => {

                document.getElementById('tagsCheckbox').innerHTML += `
                    <input type="checkbox" value="${tag.id}" class="btn-check tag" id="tag_${tag.id}" autocomplete="off">
                    <label class="btn btn-outline-warning" for="tag_${tag.id}">${tag.name}</label>
                `;

                const tagCheckbox = document.querySelectorAll(`.btn-check.tag`);

                tagCheckbox.forEach(tag => {

                    tag.addEventListener('change', (event) => {
                        if (event.target.checked) {

                            console.log('checkbox selezionata', event.target.id);

                            const tagId = event.target.value;
                            const tagName = event.target.nextElementSibling.innerHTML;

                            const tagElement = { id: tagId, name: tagName };
                            console.log('Filtro per tag', tagElement);

                            photosListQuery(tagElement, 'tags');
                        } else {
                            console.log('checkbox deselezionata', event.target.id);
                            photosList();
                        }
                    })
                })

            })

        }).catch((result) => {
            console.error("Errore nella richiesta", result);
            alert("Errore durante la richiesta");
        })

    axios.get('http://localhost:8080/api/categories')
        .then((result) => {
            const categories = result.data;
            console.log("categories", categories);

            categories.forEach(category => {
                document.getElementById('categoriesCheckbox').innerHTML += `
            
            <input type="checkbox" value="${category.id}" class="btn-check category" id="category_${category.id}" autocomplete="off">
            <label class="btn btn-outline-info" for="category_${category.id}">${category.name}</label>

            `;

                const categoryCheckbox = document.querySelectorAll(`.btn-check.category`);

                categoryCheckbox.forEach(category => {

                    category.addEventListener('change', (event) => {
                        if (event.target.checked) {
                            console.log('checkbox selezionata', event.target.id)

                            const categoryId = event.target.value;
                            const categoryName = event.target.nextElementSibling.innerHTML;

                            const categoryElement = { id: categoryId, name: categoryName };
                            console.log('Filtro per tag', categoryElement);

                            photosListQuery(categoryElement, 'categories');
                        } else {
                            console.log('checkbox deselezionata', event.target.id);
                            photosList();
                        }
                    })
                })

            })

        }).catch((result) => {
            console.error("Errore nella richiesta", result);
            alert("Errore durante la richiesta");
        })

}