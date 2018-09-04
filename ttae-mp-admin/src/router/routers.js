import Main from '@/view/main'
import parentView from '@/components/parent-view'

/**
 * iview-admin中meta除了原生参数外可配置的参数:
 * meta: {
 *  hideInMenu: (false) 设为true后在左侧菜单不会显示该页面选项
 *  notCache: (false) 设为true后页面不会缓存
 *  access: (null) 可访问该页面的权限数组，当前路由设置的权限会影响子路由
 *  icon: (-) 该页面在左侧菜单、面包屑和标签导航处显示的图标，如果是自定义图标，需要在图标名称前加下划线'_'
 * }
 */

export default [
  {
    path: '/login',
    name: 'login',
    meta: {
      title: 'Login - 登录',
      hideInMenu: true
    },
    component: () => import('@/view/login/login.vue')
  },
  {
    path: '/',
    name: '_home',
    redirect: '/home',
    component: Main,
    meta: {
      hideInMenu: true,
      notCache: true
    },
    children: [
      {
        path: '/home',
        name: 'home',
        meta: {
          hideInMenu: true,
          title: '首页',
          notCache: true
        },
        component: () => import('@/view/single-page/home')
      }
    ]
  },
  {
    path: '/security',
    name: 'security',
    meta: {
      icon: 'ios-finger-print',
      title: '权限管理',
      access: ['ROLE_ADMIN']
    },
    component: Main,
    children: [
      {
        path: 'permission_list',
        name: 'permission_list',
        meta: {
          title: '权限配置',
          access: ['ROLE_ADMIN']
        },
        component: () => import('@/view/security/permission_list.vue')
      },
      {
        path: 'role_list',
        name: 'role_list',
        meta: {
          title: '角色配置',
          access: ['ROLE_ADMIN']
        },
        component: () => import('@/view/security/role_list.vue')
      },
      {
        path: 'principal_list',
        name: 'principal_list',
        meta: {
          title: '用户配置',
          access: ['ROLE_ADMIN']
        },
        component: () => import('@/view/security/principal_list.vue')
      },
    ]
  },
  {
    path: '/401',
    name: 'error_401',
    meta: {
      hideInMenu: true
    },
    component: () => import('@/view/error-page/401.vue')
  },
  {
    path: '/500',
    name: 'error_500',
    meta: {
      hideInMenu: true
    },
    component: () => import('@/view/error-page/500.vue')
  },
  {
    path: '*',
    name: 'error_404',
    meta: {
      hideInMenu: true
    },
    component: () => import('@/view/error-page/404.vue')
  }
]
