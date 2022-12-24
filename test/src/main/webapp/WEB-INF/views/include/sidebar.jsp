<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<style>
.selected {
	background: #0b5ed7;
	color: white;
	font-weight: 900;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
<div class="border-end bg-white" id="sidebar-wrapper">
	<div class="sidebar-heading border-bottom bg-light">Start
		Bootstrap</div>
	<div class="list-group list-group-flush">
		<a
			class="${category eq 'cu' ?'selected':''} list-group-item list-group-item-action list-group-item-light p-3"
			href="list.cu">고객관리</a> <a
			class="${category eq 'hr' ?'selected':''} list-group-item list-group-item-action list-group-item-light p-3"
			href="list.hr">사원관리</a> <a
			class="list-group-item list-group-item-action list-group-item-light p-3"
			href="#!">Overview</a> <a
			class="list-group-item list-group-item-action list-group-item-light p-3"
			href="#!">Events</a> <a
			class="list-group-item list-group-item-action list-group-item-light p-3"
			href="#!">Profile</a> <a
			class="list-group-item list-group-item-action list-group-item-light p-3"
			href="#!">Status</a>
	</div>
</div>