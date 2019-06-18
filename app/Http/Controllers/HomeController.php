<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class HomeController extends Controller
{
    public function index($id)
    {
        return view('index',compact('id'));
    } 

    public function detail($id) 
    {
        $url = 'https://rickandmortyapi.com/api/character/'.$id;
        $data = json_decode(file_get_contents($url), true);
        $location =  json_decode(file_get_contents($data['location']['url']), true);
        return view('detail',compact('data','location'));
    }
}
