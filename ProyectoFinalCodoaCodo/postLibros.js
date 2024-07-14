document.addEventListener('DOMContentLoaded', async () =>{
formNuevoLibro=document.getElementById('formNuevoLibro');
formNuevoLibro.addEventListener('submit',async (event)=>{
    event.preventDefault();

    const formData = new FormData(formNuevoLibro);
    
    const titulo=formData.get('titulo');
    const autor=formData.get('autor');
    const genero=formData.get('genero');
     
    if(titulo===''||autor===''||genero===''){
        alert('todos los campos son obligatorios');
        return;
    }
    
    const options={
        method:'POST',
        headers:{
            'Content-Type':'application/json'
        },
        body:JSON.stringify({
            
            titulo:titulo,
            autor:autor,
            genero:genero,
        })
    };
    const response =await fetch('http://localhost:8080/apicodoacodo/libros',options);
    if (response.status===201){
        alert('Libro agregado correctamente');
        formNuevoLibro.reset();
        location.reload();
    }else{
        alert('Error al cargar la pelicula');
    }
});
});