<!DOCTYPE html>
<html lang="en">
<head>
    <title>Hello World</title>

    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="{{ asset('portfolio/css/bootstrap.min.css') }}">

    <!-- FontAwesome CSS -->
    <link rel="stylesheet" href="{{ asset('portfolio/css/font-awesome.min.css') }}">

    <!-- Styles -->
    <link rel="stylesheet" href="{{ asset('portfolio/style.css') }}">
</head>
<body>
    {{-- <header class="site-header">
        <div class="site-branding">
            <h1 class="site-title"><a href="index.html" rel="home"><img src="images/logo.png" alt="Logo"></a></h1>
        </div><!-- .site-branding -->

        <div class="hamburger-menu">
            <div class="menu-icon">
                <img src="images/menu-icon.png" alt="menu icon">
            </div><!-- .menu-icon -->

            <div class="menu-close-icon">
                <img src="images/x.png" alt="menu close icon">
            </div><!-- .menu-close-icon -->
        </div><!-- .hamburger-menu -->
    </header><!-- .site-header --> --}}

<div class="nav-bar-sep d-lg-none"></div>

<center>
<div class="outer-container">
    <div class="container single-portfolio">
        <div class="row">
            <div class="col-12">
                <div class="featured-img">
                    <figure>
                        @php
                            
                        @endphp
                        <img src="{{$data['image']}}" alt="">
                    </figure>
                </div><!-- .content-area -->
            </div><!-- .col-12 -->

            <div class="col-12">
                <div class="content-area">
                    <header class="entry-header">
                        <h1>{{$data['name']}}</h1>
                    </header><!-- .entry-header -->

                    <div class="entry-meta">
                        <div class="posted-date">
                            <label>CREATED DATE</label>
                            <span class="date-format" style="color:#BDBFBF;">{{ date("d/m/Y H:i", strtotime($data['created'])) }}</span>
                        </div><!-- .posted-date -->
    
                        <div class="post-category">
                            <label>STATUS</label>
                            <span style="color:#BDBFBF;">{{ $data['status'] }}</span>
                        </div><!-- .post-category -->
    
                        <div class="posted-tags">
                            <label>SPECIES</label>
                            <span style="color:#BDBFBF;">{{ $data['species'] }}</span>
                        </div><!-- .entry-meta -->
    
                        <div class="posted-tags">
                            <label>GENDER</label>
                            <span style="color:#BDBFBF;">{{ $data['gender'] }}</span>
                        </div><!-- .entry-meta -->
    
                        <div class="posted-tags">
                            <label>ORIGIN</label>
                            <span style="color:#BDBFBF;">{{ $data['origin']['name'] }}</span>
                        </div><!-- .entry-meta -->
    
                        <div class="posted-tags">
                            <label>LAST LOCATION</label>
                            <span style="color:#BDBFBF;">{{ $location['name'] }} ,{{ $location['type'] }} ,{{ $location['dimension'] }}</span>
                        </div><!-- .entry-meta -->

                    </div><!-- .entry-meta -->

                </div><!-- .content-area -->
            </div><!-- .col-12 -->
        </div><!-- .row -->

        <div class="row">
            <div class="col">
                <nav class="post-nav">
                    <ul class="flex justify-content-between align-items-center">
                        @php
                            $id = $data['id'];
                            $beforeid = $id - 1;
                            $afterid = $id + 1;
                        @endphp
                        @if ($data['id'] != 1)
                            <li><a href="{{url("detail/".$beforeid)}}"><img src="{{ asset('portfolio/images/angle-left.png') }}" alt="Previous"></a></li>
                        @else 
                        <li><a href=""><img src="" alt=""></a></li>
                        @endif
                        <li><a href="{{url('/')}}"><img src="{{ asset('portfolio/images/portfolio-icon.png') }}" alt="Back to Portfolio"></a></li>
                        <li><a href="{{url("detail/".$afterid)}}"><img src="{{ asset('portfolio/images/angle-right.png') }}" alt="Next"></a></li>
                    </ul>
                </nav><!-- .post-nav -->
            </div><!-- .col -->
        </div><!-- .row -->
    </div><!-- .container -->
</div><!-- .outer-container -->
</center>

<script type='text/javascript' src='{{ asset('portfolio/js/jquery.js') }}'></script>
<script type='text/javascript' src='{{ asset('portfolio/js/custom.js') }}'></script>

</body>
</html>