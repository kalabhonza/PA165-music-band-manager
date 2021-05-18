import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'Breadcrumbs'
})
export class BreadcrumbsPipe implements PipeTransform {

  transform(value: string, ...args: any[]): any {
    if (value) {
      if (value.charAt(0) === '/') {
        value = value.substr(1);
        value = value.charAt(0).toUpperCase() + value.substr(1).toLowerCase();
        value = value.replace(new RegExp('-', 'g'), ' ');
        if (value === 'home') {
          value = '';
        }
      }
    }
    return value;
  }
}
