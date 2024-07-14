
document.addEventListener('DOMContentLoaded', async()=>{
    const options ={
    method:'GET', 
    headers: {
    'Content-Type':'application/json'
    }
    }
    const response=await fetch('http://localhost:8080/apicodoacodo/libros', options);
    const data = await response.json();
    console.log(data);
    
    const libros=data;
    const tbody=document.getElementById('bodyTableLibros');
    libros.forEach(libro=>{
        const tr=document.createElement('tr');
        
        
        
        const tdTitle=document.createElement('td');
        tdTitle.textContent=libro.titulo;
        
        const tdAutor=document.createElement('td');
        tdAutor.textContent=libro.autor;
        
        const tdGenero=document.createElement('td');
        tdGenero.textContent=libro.genero;
        
        
        tr.appendChild(tdTitle);
        tr.appendChild(tdAutor);
        tr.appendChild(tdGenero);
        
        tbody.appendChild(tr);
        
        
    });
});