<head>
    <title>${requestScope.appName} - ${pageTitle}</title>
    <style>
    .error{color:red}
    button {
        border: none;
        color: white;
        padding: 5px 10px;
        text-align: center;
        text-decoration: none;
        display: inline-block;
        font-size: 16px;
        margin: 4px 2px;
        cursor: pointer;
    }
    .green_button {background-color: #4CAF50;} /* Green */
    .green_button:hover {background-color: #008000;} /* Dark Green */
    .blue_button {background-color: #008CBA;} /* Blue */
    .blue_button:hover {background-color: #00008B;} /* Dark Blue */
    .red_button {background-color: #f44336;} /* Red */
    .red_button:hover {background-color: #8B0000;} /* Dark Red */
    .gray_button {background-color: #A9A9A9;} /* Gray */
    .gray_button:hover {background-color: #808080;} /* Dark Gray */

	.menu {
        list-style-type: none;
        margin: 0;
        padding: 0;
        overflow: hidden;
        width: 100%;
        background-color: #E97451;
    }

    .menu li {
        float: left;
    }

    .menu li button {
        display: block;
        color: white;
        text-align: center;
        padding: 10px 10px;
        text-decoration: none;
        background-color: #E97451;
        border: none;
        cursor: pointer;
    }

    .menu li button:hover {
        background-color: #CC5500;
        font-weight: bold;
    }

    .active-menu {
        background-color: #CC5500;
        font-weight: bold;
    }

    .sub-menu {
        list-style-type: none;
        margin: 0;
        padding: 0;
        width: 100%;
        height: 100%;
        color: black;
    }

    .sub-menu li button {
        display: block;
        padding: 8px 16px;
        text-decoration: none;
        color: black;
        width: 100%;
        text-align: left;
        border: none;
        background-color: #D3D3D3;
        cursor: pointer;
    }

    /* Change the link color on hover */
    .sub-menu li button:hover {
        background-color: #899499;
        font-weight: bold;
        color: white;
    }

    .active-sub-menu {
        background-color: #899499;
        font-weight: bold;
        color: white;
    }
    </style>
</head>