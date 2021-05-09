import http from './httpLocal.common'

function getAll(apiUrl) {
    return new Promise((resolve, reject) =>{
        http.get(apiUrl)
        .then(res =>{
            resolve(res);
        })
    })
    
}

function getById(apiUrl, id) {
    return new Promise((resolve, reject) =>{
        http.get(`${apiUrl}/${id}`)
        .then(res =>{
            resolve(res);
        })
    })
}

function getChildrenById(apiUrl, id, childrenUrl) {
    return new Promise((resolve, reject) =>{
        http.get(`${apiUrl}/${id}/${childrenUrl}`)
        .then(res =>{
            resolve(res);
        })
    })
}

function create(apiUrl, data, page) {
    return http.post(`${apiUrl}`, data, {
        headers: {
            "Content-Type": page == "products" ? "multipart/form-data" : "application/json",
        }
    })
}

function update(apiUrl, id, data) {
    return http.put(`${apiUrl}/${id}/`, data);
}

function remove(apiUrl, id, data){
    return http.delete(`${apiUrl}/${id}/`, data)
}

function updateForChildren(apiUrl, id, childUrl, data) {
    return http.put(`${apiUrl}/${id}/${childUrl}`, data);
}

export default {
    getAll,
    getById,
    getChildrenById,
    update,
    updateForChildren,
    remove,
    create,
  };