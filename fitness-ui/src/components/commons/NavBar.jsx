import React from 'react'
import { Link } from 'react-router-dom'

const NavBar = () => {
  return (
    <header className='px-8 py-6 bg-red-300 text-white font-semibold w-full'>
      <nav className='flex justify-between gap-x-4 w-full'>
      <div className='flex gap-x-5'>
        <Link to="/">Home</Link>
        <Link to="/trainers-list">Trainers</Link>
        <Link to="/members-list">Members</Link>
        
      </div>
      <div className='flex gap-x-5'>
        <Link>Admin</Link>
        <Link>Login</Link>
        <Link>Logout</Link>
      </div>
      </nav>
    </header>
  )
}

export default NavBar
