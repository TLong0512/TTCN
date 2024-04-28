document.addEventListener('DOMContentLoaded', (event) => {
	

	const activeMenus = document.querySelectorAll('.side-menu--active');
	activeMenus.forEach(menu => {
		menu.classList.remove('side-menu--active');
		menu.classList.add('side-menu');
	});
	
	var path = window.location.pathname;
	var pathSegments = path.split('/');
	
	const idFromUrl = pathSegments[2];

	const menuItems = document.querySelectorAll('.side-menu');
	menuItems.forEach(menuItem => {
		if (menuItem.id === idFromUrl) {
			menuItem.classList.add('side-menu--active');
		}
	});
});