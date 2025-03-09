import Category from "./category.model";

interface Note {
  id: number;
  title: string;
  description: string;
  // categoryId: number;
  categories: Array<Category>;
}

export default Note;