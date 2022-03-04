<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<footer class="p-3 bg-dark mt-auto">
	<div class="container-fluid text-white d-flex flex-wrap">
		<div class="footer-item d-flex flex-column my-3">
			<h5>Contact</h5>
			<address>Phone: +(84) 938-798-685</address>
			<address>Email: truonggnfx13372@gmail.com</address>
		</div>
		<div class="footer-item d-flex flex-column my-3">
			<h5>Categories</h5>
			<a>Android</a>
			<a>Apple</a>
		</div>
		<div class="footer-item d-flex flex-column my-3">
			<h5>Menu</h5>
			<a href="<c:url value="/shop"></c:url>">Home</a>
			<a href="<c:url value="/shop?action=products"></c:url>">Products</a>
			<a href="<c:url value="/shop?action=aboutus"></c:url>">About Us</a>
		</div>
	</div>
	<hr class="text-white">
	<p class="text-center text-white">Copyright@2022</p>
</footer>
</body>
</html>