const API_URL = 'http://localhost:8080/api/owners';
const LOGIN_URL = 'http://localhost:8080/api/auth/login';

const loginForm = document.getElementById('login-form');
const ownerForm = document.getElementById('owner-form');
const ownerList = document.getElementById('owner-list');
const loginSection = document.getElementById('login-section');
const appSection = document.getElementById('app-section');
const logoutBtn = document.getElementById('logout-btn');
const loginError = document.getElementById('login-error');

let editingId = null;


function fetchOwners() {
  fetch(API_URL, {
    headers: { 'Authorization': `Bearer ${getToken()}` }
  })
  .then(res => {
    if (!res.ok) throw new Error('Failed to fetch owners');
    return res.json();
  })
  .then(data => {
    const tableBody = document.querySelector('#owners tbody');
    tableBody.innerHTML = ''; // Clear previous rows

    data.forEach(owner => {
      const row = document.createElement('tr');
      row.innerHTML = `
        <td>${owner.id}</td>
        <td>${owner.name}</td>
        <td>${owner.email}</td>
        <td>
          <button onclick="editOwner(${owner.id}, '${owner.name}', '${owner.email}')">Edit</button>
          <button onclick="deleteOwner(${owner.id})">Delete</button>
        </td>
      `;
      tableBody.appendChild(row);
    });
  })
  .catch(err => {
    console.error(err);
    const tableBody = document.querySelector('#owners tbody');
    tableBody.innerHTML = `<tr><td colspan="4" style="color:red;">Error loading owners</td></tr>`;
  });
}


function getToken() {
  return localStorage.getItem('token');
}

function isLoggedIn() {
  return !!getToken();
}

function showApp() {
  loginSection.style.display = 'none';
  appSection.style.display = 'block';
  fetchOwners();
}

function showLogin() {
  loginSection.style.display = 'block';
  appSection.style.display = 'none';
}

loginForm.addEventListener('submit', e => {
  e.preventDefault();
  const username = document.getElementById('username').value;
  const password = document.getElementById('password').value;

  fetch(LOGIN_URL, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ username, password })
  })
  .then(res => {
    if (!res.ok) throw new Error('Błędny login lub hasło');
    return res.json();
  })
  .then(data => {
    localStorage.setItem('token', data.token);
    showApp();
  })
  .catch(err => {
    loginError.textContent = err.message;
  });
});

logoutBtn.addEventListener('click', () => {
  localStorage.removeItem('token');
  showLogin();
});

ownerForm.addEventListener('submit', e => {
  e.preventDefault();
  const owner = {
    name: document.getElementById('name').value,
    email: document.getElementById('email').value
  };

  const method = editingId ? 'PUT' : 'POST';
  const url = editingId ? `${API_URL}/${editingId}` : API_URL;

  fetch(url, {
    method: method,
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${getToken()}`
    },
    body: JSON.stringify(owner)
  })
  .then(() => {
    document.getElementById('name').value = '';
    document.getElementById('email').value = '';
    editingId = null;
    fetchOwners();
  });
});

function fetchOwners() {
  fetch(API_URL, {
    headers: { 'Authorization': `Bearer ${getToken()}` }
  })
  .then(res => res.json())
  .then(data => {
    ownerList.innerHTML = '';
    data.forEach(owner => {
      const li = document.createElement('li');
      li.innerHTML = `
        <strong>${owner.name}</strong> (${owner.email})
        <button onclick="editOwner(${owner.id}, '${owner.name}', '${owner.email}')">Edytuj</button>
        <button onclick="deleteOwner(${owner.id})">Usuń</button>
      `;
      ownerList.appendChild(li);
    });
  });
}

function editOwner(id, name, email) {
  document.getElementById('name').value = name;
  document.getElementById('email').value = email;
  editingId = id;
}

function deleteOwner(id) {
  fetch(`${API_URL}/${id}`, {
    method: 'DELETE',
    headers: { 'Authorization': `Bearer ${getToken()}` }
  })
  .then(() => fetchOwners());
}

if (isLoggedIn()) {
  showApp();
} else {
  showLogin();
}
