import React from 'react'

const NavBar = () => {
  return (
    <header className='px-8 py-6 bg-red-300 text-white font-semibold w-full'>
      <nav className='flex justify-between gap-x-4 w-full'>
      <div className='flex gap-x-5'>
        <a>Trainers</a>
        <a>Members</a>
        
      </div>
      <div className='flex gap-x-5'>
        <a>Admin</a>
        <a>Login</a>
        <a>Logout</a>
      </div>
      </nav>
    </header>
  )
}

export default NavBar
